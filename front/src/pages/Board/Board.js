import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import PinCodeModal from "./modals/PinCodeModal";
import './Board.css'
import api from "../../services/api";

const Board = () => {
  const { code, fromcreate } = useParams();
  const [copyState, setCopyState] = useState(false)
  const [showPinModal, setShowPinModal] = useState(false)
  const [dataBoard, setDataBoard] = useState()


  const handleClick = (e) => {
    navigator.clipboard.writeText(code);
    setCopyState(true);
  };
  useEffect(()=>{
    if(fromcreate) {
      setShowPinModal(true);
    }
    const fecha = new Date();
    console.log(`${fecha.toLocaleDateString().split('/').join('-')}T${fecha.toLocaleTimeString()}`)
    api.board.getBoardByCode(code).then(data=>setDataBoard(data))
  },[])

  return (
    <div className="board">
      <header className="header-board">
        <h2>{dataBoard.name}</h2>
      </header>
      {showPinModal && <PinCodeModal code={code} copyState={copyState} setShowPinModal={setShowPinModal} handleClick={handleClick}/>}
      {!showPinModal && <div className="task-list">
        {dataBoard.tasks.map(item => (
        <div className="task">
          <div className="img-container"><img src={item.iconUrl} /></div>
          {/* <div className="task-info"><div>{item.name}</div> <div>{item.duration}</div></div> */}
          <div className="default-profile"><img src={"/img/avatars/avatar0.png"} /></div>
        </div>)
        )}
      </div>}
    </div>
  );
}

export default Board;

