/* eslint-disable react/prop-types */
import { Modal, Space, Typography } from "antd";
import { FaQuestionCircle } from "react-icons/fa";


const DeleteModal = ({ close, open, handleOk}) => {
    return (
    <>
      <Modal title={
        <>
            <Space>
                <FaQuestionCircle color="red" />
                <Typography.Text>Delete Film</Typography.Text>
            </Space>
        </>
      } open={Boolean(open)} onOk={handleOk} onCancel={close}>
        <p>Are sure you want to delete this film?</p>
       
      </Modal>
    </>
    )
}

export default DeleteModal;