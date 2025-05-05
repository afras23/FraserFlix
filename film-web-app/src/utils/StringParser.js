function encodeString(JsonData) {
    let encodedStr = "";
    Object.entries(JsonData).forEach(([key, value]) => {
        encodedStr += `${key}:${value}#`;
    })
    encodedStr = encodedStr.slice(0, -1);
    return encodedStr;
}

function decodeString(str) {
    if(str.includes("$")) {
        const ArrStr = str.split("$");
        const Film = ArrStr.map((data) => {
            const property = data.split("#");
            const parsed = {};
            property.forEach((keyValue) => {
                const [key, value] = keyValue.split(":");
                parsed[key] = value; 
            })
            return parsed;
        })
        return Film
    } else {
        const property = str.split("#");
            const parsed = {};
            property.forEach((keyValue) => {
                const [key, value] = keyValue.split(":");
                parsed[key] = value; 
            })
        return parsed;
    }
}

export {
    encodeString,
    decodeString
}