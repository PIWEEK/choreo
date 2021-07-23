export default (client) => ({
  createBoard(newBoard){
    return client
      .post("boards", newBoard )
      .then(data => {
        return data.data
      })
  },
  getBoardByCode(pincode, date){
    return client
    .get(`boards/${pincode}`)
    .then(data => data.data)
  },
  getBoardByCodeAndDate(pincode, date){
    return client
    .get(`boards/${pincode}/scheduledTasks?date=${date}`)
    .then(data => data.data)
  }
});
