
function encodeXML(JsonData) {
    let XMLString = "<film>";
    Object.entries(JsonData).forEach(([key, value]) => {
        XMLString += `<${key}>`;
        if(typeof value === 'object') {
            XMLString += encodeXML(value)
        } else {
            XMLString += value;
        }
        XMLString += `</${key}>`;
    })
    XMLString += "</film>"
    return XMLString;
}

function decodeXML(xmlData) {
    const XMLparser = new DOMParser();
    const XMLDOM = XMLparser.parseFromString(xmlData, 'text/xml');
    const selectFilmObj = XMLDOM.querySelectorAll('film');
    const Films = Array.from(selectFilmObj).map((selectedFilm) => ({
        id: selectedFilm.querySelector('id').textContent,
        title: selectedFilm.querySelector('title').textContent,
        director: selectedFilm.querySelector('director').textContent,
        stars: selectedFilm.querySelector('stars').textContent,
        review: selectedFilm.querySelector('review').textContent,
        year: selectedFilm.querySelector('year').textContent
    }))
    return Films.length === 1 ? Films[0] : Films;
}

export {
    encodeXML,
    decodeXML
}