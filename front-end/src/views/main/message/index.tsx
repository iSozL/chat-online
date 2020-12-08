import React, {useContext} from 'react';
import {changeUserContext} from '../store/index'
import './index.scss'
const Message = () => {
  const { userMsg, useDispatch } = useContext(changeUserContext)
  return (
    <div className="msg-container">
      {
        userMsg.show ? 
        <div className="msg-body">
          <div className="msg-header">
            {userMsg.username}
          </div>
        </div> :
        <img className="none-msg" src={require('../../../assets/imgs/msg-bg.svg')} />
      }
      <div className="msg-footer"></div>
    </div>
  )
}

export default Message;