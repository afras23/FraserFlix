/* eslint-disable react/prop-types */
import { Content } from "antd/es/layout/layout";
import video from "../../assets/video.mp4";
import mainImg from "../../assets/image2.webp"
import { FaPlay, FaPlus } from "react-icons/fa";
import { Card, Col, Pagination, Row, Skeleton, Spin, Typography, notification } from "antd";
import Meta from "antd/es/card/Meta";
import { useEffect, useMemo, useState } from "react";
import FilmModal from "../../Components/AddNewModal";
import { deleteFilm, getAllFilms, getFilmData } from "../../api";
import { TbEditCircle } from "react-icons/tb";
import { MdDelete } from "react-icons/md";
import DeleteModal from "../../Components/DeleteModal";
import ImageWithLoading from "../../Components/ImageWithLoading";

const LandingPage = ({ format, search }) => {
    const [mode, setMode] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [pageSize, setPageSize] = useState(20);
    const [allFilms, setAllFilms] = useState([]);
    const [rowData, setRowData] = useState([]);
    const [deleteOpen, setDeleteOpen] = useState(null);
    const [editValue, setEditValue] = useState({});
    const [loading, setLoading] = useState(false);
    const [fullScreenLoad, setFullScreenLoad] = useState(false);
    const closeDeleteOpen = () => {
        setDeleteOpen(null);
    };

    const allFilmSearched = useMemo(() => {
        if(search) {
            return allFilms.filter((item) => {
                if(item && item?.title) {
                    return item?.title?.toLowerCase()?.includes(search?.toLowerCase());
                } 
                return item;
            })
        }
        return allFilms;
    }, [allFilms, search])

    const getFilmsData = async () => {
        try {
            setLoading(true)
            const data = await getAllFilms(format);
            setAllFilms(data);
        } catch (ex) {
            notification.error({ message: ex, placement: 'top'});
        } finally {
            setLoading(false);
        }
    }
    useEffect(() => {
        getFilmsData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [/*setAllFilms,*/ format])

    useEffect(() => {
        const loadFilmsImages = async () => {
            try {
            setLoading(true)
            const startIndex = (currentPage - 1) * pageSize;
            const endIndex = startIndex + pageSize;
            const currentItems = allFilmSearched.slice(startIndex, endIndex);
            if(currentItems.length) {    
                const promises = currentItems.map(async (item) => {
                    const data = await getFilmData(item?.title);
                    return {...item, img: data?.results?.[0]?.poster_path || ''}
                });
                // Wait for all promises to resolve
                const result = await Promise.all(promises);
                setRowData(result);
            }
            } catch (ex) {
                notification.error({ message: ex, placement: 'top'});
            } finally {
                setLoading(false);
            }
        }
        if (allFilms.length) loadFilmsImages();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [currentPage, allFilmSearched, allFilms, pageSize, search]);

    const total = allFilmSearched.length ? (allFilmSearched.length - 1) : 0;

    const handlePageChange = (page, pageSize) => {
        setCurrentPage(page);
        setPageSize(pageSize)
        console.log(currentPage)
    }
    
    const closeAddNew = () => {
        setMode(null)
    }

    const handleDelete = async () => {
        try {
            closeDeleteOpen()
            setFullScreenLoad(true)
            const response = await deleteFilm(deleteOpen, format);
            getFilmsData();
            notification.success({ message: response, placement: 'top'});
        } catch (ex) {
            notification.error({ message: ex, placement: 'top'});
        } finally {
            setFullScreenLoad(false)
        }
    }
    return (
            <Spin spinning={fullScreenLoad} tip="Loading..." size="large">
            <Content>
                <section className="main-section">
                    <video src={video} autoPlay muted loop className="video" />
                    <div className="main-content">
                        <img src={mainImg} />
                        <div>
                            <button className="btn btn-white"> 
                                <FaPlay className="ico" /> Play
                            </button>
                            <button onClick={() => setMode("Add")} className="btn btn-grey"> 
                            <FaPlus className="ico" />
                             Add New Film 
                             </button>
                        </div>
                    </div>
                    <div className="main-bottom"  />
                </section>
                <Spin spinning={loading} tip="Loading..." size="large">
                <section className="row-section">
                    <Typography.Title> Movies</Typography.Title>
                    <Row>
                        {rowData?.map((item, key) => (
                       <Col key={key} style={{ marginBottom: 70}}>
                        <Card
                            hoverable
                            style={{ 
                                width: 200,
                            }}
                            key={key}
                            cover={item?.img ? <ImageWithLoading url={`https://image.tmdb.org/t/p/original/${item?.img}`} />: <Skeleton.Image style={{ width: 200, height: 300}} active /> }
                        >
                            <Meta title={item?.title} description={
                            <>
                                    <p>{item?.director} - {item?.year}</p>
                                    <p>{item?.stars}</p>
                                    <p className="card-review">{item?.review}</p>
                                    
                                    <Row justify="space-around">
                                        <Col>
                                            <TbEditCircle onClick={() => {
                                                setMode('Edit')
                                                setEditValue(item)
                                            }} className="edit" key="edit" />,
                                            </Col>
                                            <Col>
                                            <MdDelete className="delete" onClick={() => setDeleteOpen(item?.id)} key="delete" />,
                                        </Col>
                                    </Row>
                            </>
                            }/>
                        </Card>
                        </Col>))}
                       
                    </Row>
                    <Row style={{ marginBottom: 50}} justify="end">
                        <Col>
                            {allFilmSearched.length && 
                            <Pagination current={currentPage} pageSize={pageSize} onChange={handlePageChange} total={total} />}
                        </Col>
                    </Row>
                </section>
                </Spin>
                <DeleteModal open={deleteOpen} handleOk={handleDelete} close={closeDeleteOpen} />
                <FilmModal open={mode} handleCancel={closeAddNew} format={format} oldValues={editValue} />
            </Content>
            </Spin>
    )
}

export default LandingPage;