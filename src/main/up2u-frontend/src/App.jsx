import './App.css'
import { HashRouter as Router, Routes, Route} from 'react-router-dom'
import { Home } from './pages/home'
import { Home2 } from './pages/home2'
import { AboutUs } from './pages/about-us'
import { Login } from './pages/login'
import { SignUp } from './pages/signup'
import { Layout } from './components/Layout'
import { Test } from './pages/test'

function App() {
  
  return (
    <>
      <Router>
        <Routes>
          <Route element={<Layout/>}>
          <Route path="/" element={<Home/>}/>
          <Route path="/about-us" element={<AboutUs/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/signup" element={<SignUp/>}/>
          <Route path="/home2" element={<Home2/>}/>
          <Route path="/test" element={<Test/>}/>
          </Route>
        </Routes>
      </Router>
    </>
  )
}

export default App
