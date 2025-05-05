import { decodeString } from "../utils/StringParser";
import { decodeXML } from "../utils/XMLParser";

const API_KEY = import.meta.env.VITE_TMDB_API_KEY;
const BASE_URL = import.meta.env.VITE_API_BASE_URL 
export async function getFilmData(query) {
    const data = await fetch(`https://api.themoviedb.org/3/search/movie?query=${query}&api_key=${API_KEY}`)
    const response = await data.json();
    return response;
}

export async function getAllFilms(accept) {
    const data = await fetch(`${BASE_URL}/film-rest-api/films`, {
        method: 'GET',
        headers : {
            'Content-Type': accept,
            Accept: accept
        }
    })
    const response = await data.text();
    if(accept.includes('text/xml')) {
        return decodeXML(response);
    } else if(accept.includes('application/json')) {
        return JSON.parse(response);
    } else {
        return decodeString(response);
    }
}

export async function getFilmById(id, accept) {
    const data = await fetch(`${BASE_URL}/film-rest-api/films?id=${id}`, {
        method: 'GET',
        headers : {
            'Content-Type': accept,
            Accept: accept
        }
    })
    const response = await data.text();
    if(accept.includes('text/xml')) {
        return decodeXML(response);
    } else if(accept.includes('application/json')) {
        return JSON.parse(response);
    } else {
        return decodeString(response);
    }
}

export async function AddFilm(body, accept) {
    const data = await fetch(`${BASE_URL}/film-rest-api/films`, {
        method: 'POST',
        headers : {
            'Content-Type': accept,
            Accept: accept
        },
        body
    })
    const response = await data.text();
    return response;
}

export async function editFilm(body, accept) {
    const data = await fetch(`${BASE_URL}/film-rest-api/films`, {
        method: 'PUT',
        headers : {
            'Content-Type': accept,
            Accept: accept
        },
        body
    })
    const response = await data.text();
    return response;
}

export async function deleteFilm(id, accept) {
    const data = await fetch(`${BASE_URL}/film-rest-api/films?id=${id}`, {
        method: 'DELETE',
        headers : {
            'Content-Type': accept,
            Accept: accept
        }
    })
    const response = await data.text();
    return response;
}