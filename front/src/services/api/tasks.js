export default (client) => ({
  getAllTasksByCategory(){
    return client
      .get("main-categories")
      .then(data => {
        console.log(data.data)
        return data.data
      })
  }
});
