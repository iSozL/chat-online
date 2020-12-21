import axios from 'axios';
import { message } from 'antd';

const request = axios.create({
  timeout: 1000000
})

const getToken = () => `passport ${window.localStorage.getItem("token")}` || ""

request.interceptors.request.use((config) => {
  config.headers.Authorization = getToken()
  return config
}, (error) => {
  message.error(String(error))
  return Promise.reject(error)
})

request.interceptors.response.use((res) => res, (error) => {
  message.error(String(error))
  return Promise.reject(error);
});

export default request