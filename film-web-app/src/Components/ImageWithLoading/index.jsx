/* eslint-disable react/prop-types */
import { Skeleton } from "antd"
import { useState } from "react";

const ImageWithLoading = ({ url }) => {
    const [loaded, setLoaded] = useState(false)
  
    return (
      <>
     { loaded ? null:
        <Skeleton.Image style={{ width: 200, height: 300}} active />}
        <img
        src={url}
        style={loaded ? {} : { display: 'none' }}
        onLoad={() => setLoaded(true)}
      />
      </>
    )
  }

  export default ImageWithLoading;