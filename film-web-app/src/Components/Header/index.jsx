/* eslint-disable react/prop-types */
import { Button, Col, Drawer, Input, Row, Select } from "antd";
import { Header } from "antd/es/layout/layout";
import logo from "../../assets/logo.png"
import { HiMenuAlt4 } from "react-icons/hi";
import { useEffect, useState } from "react";
import { AiOutlineSearch } from "react-icons/ai";


const AppHeader = ({ format, setFormat, setSearch}) => {
    const [drawer, setDrawer] = useState(false);
    const [inputValue, setInputValue] = useState("");

    useEffect(() => {
        const handler = setTimeout(() => {
            setSearch(inputValue);
        }, 300); // Debounce time is set to 300 milliseconds
    
        return () => {
          clearTimeout(handler); // Clear the timeout if the component is unmounted or the value changes
        };
      }, [inputValue, setSearch]);

    return (
        <>
           <Header className="header">
                <Row className="header-row" justify="space-between">
                    <Col>
                        <img className="header-logo" src={logo} />
                    </Col>
                    <Col span={6} xxl={6} xl={6} lg={0} md={0} sm={0} xs={0} >
                        <Row justify="space-between" align="middle">
                            <Col>
                                <Input placeholder="Search Film..." style={{ marginTop: '15px'}} value={inputValue} allowClear addonBefore={<AiOutlineSearch />} onChange={(e) => setInputValue(e.target.value)} />
                            </Col>
                            {/* <Col className="mt-12">
                                <CiSearch  className="search-icon" />
                            </Col> */}
                            <Col>
                            <Select
                                className="header-dropdown"
                                value={format}
                                style={{ width: 120 }}
                                onChange={(e) => setFormat(e)}
                                options={[
                                    { value: 'application/json', label: 'Json' },
                                    { value: 'text/xml', label: 'XML' },
                                    { value: 'text/string', label: 'String' },
                                ]}
                            />
                            </Col>
                        </Row>
                    </Col>
                    <Col xxl={0} xl={0} lg={1} md={1} sm={1} xs={1} className="m-t-10">
					<Row justify="center">
						<Col>
							<Button type="link" className="p-0" onClick={() => setDrawer(!drawer)}>
								<HiMenuAlt4 color="red" size={24} />
							</Button>
						</Col>
					</Row>
				</Col>
                </Row>
                <Drawer
                    title={
                        <Col>
                            <img className="header-logo" src={logo} />
                        </Col>
                    }
                    placement="right"
                    closable={false}
                    onClose={() => setDrawer(false)}
                    open={drawer}
                    >
                   
                    <Col className="mt-12">
                            <Input value={inputValue} allowClear addonBefore={<AiOutlineSearch />} onChange={(e) => setInputValue(e.target.value)} />
                    </Col>
                    <Col className="mt-12">
                            <Select
                                className="header-dropdown"
                                value={format}
                                style={{ width: 120 }}
                                onChange={(e) => setFormat(e)}
                                options={[
                                    { value: 'application/json', label: 'Json' },
                                    { value: 'text/xml', label: 'XML' },
                                    { value: 'text/string', label: 'String' },
                                ]}
                            />
                            </Col>
                </Drawer>
           </Header>
        </>
    )
}

export default AppHeader;