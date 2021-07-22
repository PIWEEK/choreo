import "./AvatarSelector.css"
const AvatarSelector = ({handler})=> {
  return (
    <div className="avatar-selector">
      <div className="header">
        <h3>Elige avatar</h3>
      </div>
      <div className="avatares">
        {
          [1,2,3,4,5,6,7,8,9].map(item=><div key={`avatar-${item}`} className="avatar" onClick={(e) => handler(item)}><img src={`./img/avatars/avatar${item}.png`}></img></div>)
        }
      </div>
    </div>
  )
}
export default AvatarSelector;