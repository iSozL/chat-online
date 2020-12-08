import React, {useContext} from 'react';
import {changeUserContext} from '../store/index'
import './index.scss'
import { Button, Input } from 'antd'
const { TextArea } = Input

const Message = () => {
  const { userMsg, useDispatch } = useContext(changeUserContext)
  return (
    <div className="msg-container">
      {
        userMsg.show ? 
        <>
        <div className="msg-body">
          <div className="msg-header">
            {userMsg.username}
          </div>  
        </div>
        <div className="msg-footer">
          <div className="footer-top">
            <img style={{width: "30px", height: "30px"}} src={require('../../../assets/imgs/face.svg')}></img>
          </div>
          <div className="footer-body">
            <TextArea rows={6} />
            <Button style={{float: 'right', marginTop: "10px"}} type="primary">发送</Button>
          </div>
          <div className="footer-bottom">
          </div>
        </div>
      </> :
        <img className="none-msg" src={require('../../../assets/imgs/msg-bg.svg')} />
      }
    </div>
  )
}

export default Message;