export default (client) => ({
  createBoard(newBoard){
    return client
      .post("boards", newBoard )
      .then(data => {
        console.log('Nuevo Tablero', data.data)
        return data.data
      })
  }
});
