import axios from "axios";
import Tasks from "./tasks";

const baseURL = "http://localhost:8080/";

export const client = axios.create({});

client.defaults.baseURL = baseURL;
client.defaults.headers["Content-Type"] = "application/json";
client.defaults.headers.Accept = "application/json";

export default {
  tasks: Tasks(client)  
};
