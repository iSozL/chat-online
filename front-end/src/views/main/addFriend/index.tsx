import React, {useState} from 'react';
import { Input, message, Button, Modal } from 'antd';
const { Search } = Input
import request from '../../../utils/request'
import './index.scss'

const AddFriend = () => {
  const [userContent, setContent] = useState<any>()
  const [visible, setVisible] = useState<boolean>(false);
  const onSearch = async (value: string) => {
    const {data} = await request.get(`http://101.132.134.186:8080/find?userId=${value}`)
    if(data.code) {
      setContent(data.data)
      message.success(data.message)
    } else {
      message.error(data.message)
    }
  }

  return (
    <div className="search-container">
      <Search placeholder="请输入查找的昵称" onSearch={onSearch} style={{width: 600}} />
      <Modal
        title="添加好友"
        centered
        visible={visible}
        onOk={() => setVisible(false)}
        onCancel={() => setVisible(false)}
        width={500}
      >
        <p>some contents...</p>
        <p>some contents...</p>
        <p>some contents...</p>
      </Modal>
      <div style={{width: "600px", height: "400px", marginTop: "60px"}} className="add-content">
        {
          userContent ? 
          <div className="add-msg">
            <img style={{width: "70px", paddingLeft: "10px"}} src={require('../../../assets/imgs/avater.svg')} />
            <div style={{height: "50px", paddingLeft: "10px"}}>
              <div>昵称：{userContent.nickname}</div>
              <div>性别：{userContent.sex}</div>
            </div>
            <div style={{marginLeft: "10%"}}>
              <Button type="primary" onClick={() => {setVisible(true)}}>添加好友</Button>
            </div>
          </div> :
          ""
        }
      </div>
    </div>
  )
}

export default AddFriend