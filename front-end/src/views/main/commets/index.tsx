import React, {useEffect, useState} from 'react';
import './index.scss'
import {message, Popconfirm, Switch} from 'antd'
import request from '../../../utils/request'
import Scroll from 'react-custom-scrollbars'
const Comments = () => {
  let info = JSON.parse(window.localStorage.getItem("userInfo"))
  const [commentList, setList] = useState<any>()
  const [visible, setV] = useState<boolean>()
  async function getMes() {
    await request.get(`http://101.132.134.186:8080/ShowImage?userId=${info.userId}`).then(async value => {
      if (value.data.code) {
        setList(value.data.data)
        setV(!Boolean(value.data.imageMark))
      } else {
        message.error(value.data.message)
      }
    })
  }
  async function confirmDel(msg: any) {
    await request.get(`http://101.132.134.186:8080/DelReceiveImage?userId=${info.userId}&friendId=${msg.userId}&time=${msg.sendtime}`).then(async value => {
      if (value.data.code) {
        message.success(value.data.message)
        getMes()
      } else {
        message.error(value.data.message)
      }
    })
  }
  async function onChange(value: boolean) {
    let note = String(Number(!value))
    await request.get(`http://101.132.134.186:8080/ChangMark?userId=${info.userId}&note=${note}`).then(async value => {
      if (value.data.code) {
        message.success(value.data.message)
        getMes()
      } else {
        message.error(value.data.message)
      }
    })
  }
  useEffect(() => {
    getMes()
  }, [])
  return (
    <div className="comment-container">
      <div className="comments-body">
        <Scroll style={{width: "600px", wordBreak: "break-all", background: '#d5d8db', borderRadius: "10px"}}>
          <div style={{margin: "30px 0 10px 0"}}>
            {
              commentList && commentList instanceof Array ?
              commentList.map((item: any, index: number) => {
                return (
                  <div className="comment" key={index}>
                    <div>
                      {item.message}
                    </div>
                    <div>{item.sendtime}</div>
                    <div style={{display: "flex", justifyContent: "space-between"}}>
                      <span>留言人 ID：{item.userId}</span>
                      <Popconfirm
                        title="确认删除这条留言吗?"
                        onConfirm={() => confirmDel(item)}
                        okText="确认"
                        cancelText="取消"
                      >
                        <a href="#" className="delete">删除</a>
                      </Popconfirm>
                    </div>
                  </div>
                )
              }) :
              "暂无好友印象"
            }
          </div>
        </Scroll>
        <div style={{position: "fixed", alignItems: "flex-end", top: "70px"}}>
          对好友可见？<Switch checked={visible} onChange={onChange} />
        </div>
      </div>
    </div>
  )
}

export default Comments