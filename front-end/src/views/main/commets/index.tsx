import React, {useEffect, useState} from 'react';
import './index.scss'
import {message} from 'antd'
import request from '../../../utils/request'
const Comments = () => {
  const [commentList, setList] = useState()
  async function getMes(id: string) {
    await request.get(`http://101.132.134.186:8080/ShowFriendImage?friendId=${id}`).then(async value => {
      if (value.data.code) {
        setList(value.data.data)
      } else {
        message.error(value.data.message)
      }
    })
  }
  useEffect(() => {
    getMes
  }, [])
  return (
    <div className="comment-container">
      留言
    </div>
  )
}

export default Comments