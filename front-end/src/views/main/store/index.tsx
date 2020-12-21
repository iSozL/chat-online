import React, { createContext, useReducer } from 'react';

export const changeUserContext = createContext({});

export const CHANGE_USER = "CHANGE_USER";

const reducer = (state: any, action: any) => {
  switch(action.type) {
    case CHANGE_USER:
      console.log(state)
      let tmp = JSON.parse(JSON.stringify(state))
      return Object.assign(tmp, action.state)
    default:
      return state
  }
}

export const Container = (props: any) => {
  const [userMsg, useDispatch] = useReducer(reducer, {
    show: false,
    username: undefined,
    userId: undefined,
    msgs: [],
    msgList: []
  })
  return (
    <changeUserContext.Provider value={{userMsg, useDispatch}}>
      { props.children }
    </changeUserContext.Provider>
  )
}