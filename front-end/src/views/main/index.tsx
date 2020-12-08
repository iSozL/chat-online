import React, { useState } from 'react';
import './index.scss'
import FriendContent from './friendContent/index';
import Message from './message/index'
import { Container } from './store/index'
import AddFriend from './addFriend/index'

const Main: React.FC = () => {
  const [select, setSelect] = useState<string>("me")
  return (
    <div className="main">
      <div className="main-container">
        <div className="aside">
          <div className="avater">
            <img onClick={() => {setSelect("me")}} src={require('../../assets/imgs/avater.svg')} />
          </div>
          <div>
            <img className="me" onClick={() => {setSelect("add")}} src={require('../../assets/imgs/me.svg')} />
          </div>
        </div>
        <div className="main-body">
          {
            (() => {
              switch(select) {
                case "me":
                  return (
                    <Container>
                      <FriendContent />
                      <Message />
                    </Container>
                  )
                case "add":
                  return (
                    <Container>
                      <AddFriend />
                    </Container>
                  )
                default:
                  return (
                    <Container>
                      <FriendContent />
                      <Message />
                    </Container>
                  )
              }
            })()
          }
        </div>
      </div>
    </div>
  )
}

export default Main