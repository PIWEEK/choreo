
import { useEffect, useState } from 'react';
import api from "../../services/api"

import './TasksList.css'


const TasksList = ({preselectedTasksList, setPreselectedTasksList, createBoard}) => {
  const [tasksList, setTasksList] = useState([])
  // const [preselectedTasksList, setPreselectedTasksList] = useState([])
  useEffect(()=>{
    api.tasks.getAllTasksByCategory().then(data=>setTasksList(data))
  },[])

  const preselectTask = (id) => {
    if(preselectedTasksList.includes(id)){
      setPreselectedTasksList( preselectedTasksList.filter(x=> x !== id))
    } else {
      setPreselectedTasksList([...preselectedTasksList, id])
    }
  }
  return (
    <div className="category-list">
      {tasksList.map(category=><div className="category" key={`categoria-${category.id}`}>
        <h3>{category.name}</h3>
        <div className="tasks-list">
          {category.tasks.map(task => <div className={`task ${preselectedTasksList.includes(task.id)?'selected':''}`} key={`task-${task.id}`} onClick={(e)=>preselectTask(task.id)}>
            <img src={task.iconUrl} />
            <h4>{task.name}</h4>
          </div>)}
        </div>
      </div>)}
      <button onClick={()=>createBoard()}>Guardar</button>
    </div>
  )
}

export default TasksList;
