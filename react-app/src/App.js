import logo from './logo.svg';
import './App.css';
import Home from './Components/Home';
import Dashboard from './Components/Dashboard';
import { BrowserRouter, Routes, Route} from "react-router-dom";


function App() {
  return (
    // <div className="App">
    //   <Home/>
    // </div>
  
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/dashboard" element={<Dashboard />} />
    </Routes>
  </BrowserRouter>
   
  );
}

export default App;
