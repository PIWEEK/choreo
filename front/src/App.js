import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import Home from "./pages/Home/Home"
import NewBoard from "./pages/NewBoard/NewBoard"
import Board from "./pages/Board/Board";
import './App.css';

function App() {
  return (
    <Router>
      <div className="container">
        <Switch>
          <Route exact path="/">
            <Home />
          </Route>
          <Route exact path="/new-board">
            <NewBoard />
          </Route>
          <Route exact path="/board/:code/:fromcreate?">
            <Board />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
