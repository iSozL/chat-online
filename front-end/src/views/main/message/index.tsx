import { url } from 'inspector';
import React from 'react';
import './index.scss'
const Message = () => {
  return (
    <div className="msg-container">
      {/* <img className="none-msg" src={require('../../../assets/imgs/msg-bg.svg')} /> */}
      <div className="msg-body"></div>
      <div className="msg-footer"></div>
    </div>
  )
}

export default Message;