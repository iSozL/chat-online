import axios from 'axios';
import { notification } from 'antd';

const request = axios.create({
  timeout: 10000
})

const getToken = () => `passport ${window.localStorage.getItem("token")}` || ""

request.interceptors.request.use((config) => {
  config.headers.Authorization = getToken()
  return config
}, (error) => {
  notification.error(
    {
      message: 'Error',
      description: String(error)
    }
  )
  return Promise.reject(error)
})

request.interceptors.response.use((res) => res, (error) => {
  notification.error({
    message: 'Error',
    description: String(error),
  });
  return Promise.reject(error);
});

export default request