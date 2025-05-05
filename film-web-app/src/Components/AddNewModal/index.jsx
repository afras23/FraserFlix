/* eslint-disable react/prop-types */
import { Button, Form, Input, Modal, Row, Space, message } from "antd";
import TextArea from "antd/es/input/TextArea";
import { useEffect, useState } from "react";
import { AddFilm, editFilm, getFilmById, getFilmData } from "../../api";
import { encodeXML } from "../../utils/XMLParser";
import { encodeString } from "../../utils/StringParser";


const FilmModal = ({ open, handleCancel, oldValues, format}) => {
    const [loading, setLoading] = useState(false);
    const [form] = Form.useForm();
    const title = Form.useWatch('title', form);
    useEffect(() => {
        async function getData() {
            const data = await getFilmData(title);
            console.log(data);
        }
        getData();
    }, [title]);
    const optimisticUpdate = async (id) => {
        try {
            const response = await getFilmById(id, format);
            console.log(response);
            form.setFieldValue('id', response?.id);
            form.setFieldValue('title', response?.title);
            form.setFieldValue('stars', response?.stars);
            form.setFieldValue('review', response?.review);
            form.setFieldValue('year', response?.year);
            form.setFieldValue('director', response?.director);
        } catch (ex) {
            message.error({ message: ex, placement: 'top'});
        }
    }
    useEffect(() => {
        if(open === 'Edit' && oldValues && Object.keys(oldValues).length) {
            optimisticUpdate(oldValues?.id);
            form.setFieldValue('id', oldValues?.id);
            form.setFieldValue('title', oldValues?.title);
            form.setFieldValue('stars', oldValues?.stars);
            form.setFieldValue('review', oldValues?.review);
            form.setFieldValue('year', oldValues?.year);
            form.setFieldValue('director', oldValues?.director);
        } else {
            form.setFieldValue('title', '');
            form.setFieldValue('stars', '');
            form.setFieldValue('review', '');
            form.setFieldValue('year', '');
            form.setFieldValue('director', '');
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [form, oldValues, open])
    const onFinish = async (data) => {
        try {
            setLoading(true);
            let body = '';
            if(format.includes('text/xml')) {
                body = encodeXML(data);
            } else if(format.includes('application/json')) {
                console.log(format)
                body = JSON.stringify(data);
            } else {
                body = encodeString(data);
            }
            const response = open === 'Add' ? await AddFilm(body, format) : await editFilm(body, format);
            message.success(response);
            handleCancel()
        } catch (ex) {
            console.log(ex)
            message.error(ex);
        } finally {
            setLoading(false);
        }
    }
    return  (
        <>
            <Modal
                title={`${open} Film`}
                open={open !== null ? true : false}
                footer={null}
                onCancel={handleCancel}
                confirmLoading={loading}
            >
                <Form
                    form={form}
                    layout="vertical"
                    name="control-hooks"
                    onFinish={onFinish}
                    style={{ maxWidth: 600 }}
                    >

                {open === 'Edit' && <Form.Item name="id">
                    <Input type="hidden" />
                </Form.Item>}
                <Form.Item name="title" label="Title" rules={[{ required: true }]}>
                    <Input />
                </Form.Item>

                <Form.Item name="stars" label="Stars" rules={[{ required: true }]}>
                    <Input />
                </Form.Item>
                <Form.Item name="director" label="Director" rules={[{ required: true }]}>
                    <Input />
                </Form.Item>
                <Form.Item name="year" label="Year" rules={[{ required: true }]}>
                    <Input />
                </Form.Item>
                <Form.Item name="review" label="Review" rules={[{ required: true }]}>
                    <TextArea
                        autoSize={{ minRows: 3, maxRows: 5 }}
                    />
                    </Form.Item>
                <Row justify="end">
                    <Space>
                        <Button type="default" onClick={handleCancel}>Cancel</Button>
                        <Button loading={loading} htmlType="submit" type="primary">Submit</Button>
                    </Space>
                </Row>
                </Form>
            </Modal>
        </>
    )
}

export default FilmModal;