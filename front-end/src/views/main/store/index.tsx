import React, { createContext, useReducer } from 'react';

export const changeUserContext = createContext({});

export const CHANGE_USER = "CHANGE_USER";

const reducer = (state: any, action: any) => {
  let tmp = JSON.parse(JSON.stringify(state))
  switch(action.type) {
    case CHANGE_USER:
      return Object.assign(tmp, action.state)
    default:
      return state
  }
}

export const Container = (props: any) => {
  const [userMsg, useDispatch] = useReducer(reducer, {
    show: false,
    username: "",
    userId: "",
    msgs: [],
    msgList: [],
    curMes: ""
  })
  return (
    <changeUserContext.Provider value={{userMsg, useDispatch}}>
      { props.children }
    </changeUserContext.Provider>
  )
}