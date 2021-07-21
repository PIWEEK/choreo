import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import Home from "./pages/Home/Home"
import NewBoard from "./pages/NewBoard/NewBoard"
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
        </Switch>
      </div>
    </Router>
  );
}

export default App;
