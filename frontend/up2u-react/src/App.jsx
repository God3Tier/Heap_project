import './App.css'
import { HashRouter as Router, Routes, Route} from 'react-router-dom'
import { Layout } from './components/Layout'
import { Test } from './pages/test'
import { Home } from './pages/home'
import { AboutUs } from './pages/about-us'
import { Login } from './pages/login'
import { SignUp } from './pages/signup'
import { Search } from './pages/search'
import { Reviews } from './pages/reviews'
import { ListReviews } from './pages/list-reviews'


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
          <Route path="/search" element={<Search/>}/>
          <Route path="/reviews" element={<Reviews/>}/>
          <Route path="/list-reviews" element={<ListReviews/>}/>
          <Route path="/test" element={<Test/>}/>
          </Route>
        </Routes>
      </Router>
    </>
  )
}

export default App
