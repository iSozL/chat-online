import React, { useContext, useRef } from 'react';
import {changeUserContext} from '../store/index'
import './index.scss'
import { Button, Popover } from 'antd'
// const { TextArea } = Input
import Scroll from 'react-custom-scrollbars'
import 'emoji-mart/css/emoji-mart.css'
import { Picker } from 'emoji-mart'

const Message = () => {
  const { userMsg, useDispatch } = useContext(changeUserContext)
  const inputs = useRef<any>()
  const emoji = (
    <div>
      <Picker onSelect={(emoji: any)=> {inputs.current.value += emoji.native}} />
    </div>
  )
  return (
    <div className="msg-container">
      {
        userMsg.show ? 
        <>
        <div className="msg-body">
          <div className="msg-header">
            {userMsg.username}
          </div>
          <div>
            <Scroll style={{height: "45vh", width: "99%", display: "flex"}}>
              <div style={{padding: "10px 30px"}}>
                <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                <div className="arrow-box popper border arrow-left">
                  我徒弟呢？
                </div>
              </div>
              <div style={{padding: "10px 30px", flex: 1, textAlign: "right"}}>
                <div className="arrow-box popper border arrow-right">
                  我不到啊
                </div>
                <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
              </div>
              <div style={{padding: "10px 30px"}}>
                <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
                <div className="arrow-box popper border arrow-left">
                  我徒弟呢？
                </div>
              </div>
              <div style={{padding: "10px 30px", flex: 1, textAlign: "right"}}>
                <div className="arrow-box popper border arrow-right">
                  我不到啊
                </div>
                <img style={{width: "50px"}} src={require('../../../assets/imgs/avater.svg')} />
              </div>
            </Scroll>
          </div>
        </div>
        <div className="msg-footer">
          <div className="footer-top">
            <Popover content={emoji} placement="leftTop">
              <img style={{width: "30px", height: "30px"}} src={require('../../../assets/imgs/face.svg')}></img>
            </Popover>
          </div>
          <div className="footer-body">
            <textarea className="ant-input" ref={inputs} />
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