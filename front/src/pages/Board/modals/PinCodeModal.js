import "./PinCodeModal.css"
const PinCodeModal = ({code, copyState, setShowPinModal, handleClick})=> (
  <div className="modal">
    <span className="close" onClick={()=>setShowPinModal(false)}>x</span>
    <h3>Comparte el tablero con otras personas para que también puedan ver y gestionar las tareas de este tablero.</h3>
    <div className="pin">
      <h4><span>PIN:</span>{code}</h4>
    </div>
    <button onClick={handleClick} className={!copyState?'':'copiado'}>{copyState?'Copiado':'Copiar Enlace'}</button>
  </div>
)
export default PinCodeModal;