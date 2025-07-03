import './App.css';
import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Sidebar from './Components/Bars/Sidebar';
import Navbar from './Components/Bars/Navbar';
import Home from './Components/Home/HomePage';
import Heading from './Components/Heading';
import DHeading from './Components/TileCalculation/DHeading';
import InHeading from './Components/Invoice/InHeading';
import PHeading from './Components/PaintCalculation/PHeading';
import RcHeading from './Components/RccCalculation/RcHeading';
import BHeading from './Components/Bathfixing/BHeading';
import SHeading from './Components/SwitchMatrix/SHeading';
import WeeklyPaymentHeading from './Components/Accounts/WeeklyPaymentHeading';
import RHeading from './Components/RentManagement/RHeading';
import MHeading from './Components/MasonaryCalculater/MHeading';
import CHeading from './Components/CarpentryCalculation/CHeading';
import LoginPage from './LoginPages/Login';
import BillHeading from './Components/BillChecklist/BillHeading';
import PurchaseHeading from './Components/Purchase/PurchaseHeading';
import ManageHeading from './Components/ManageUsers/ManageHeading';
import Attendancelog from './Components/Attendances/Attendancelog';
function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser] = useState(null);
  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      setUser(JSON.parse(storedUser)); // Parse back to object
      setIsLoggedIn(true);
    }
  }, []);
  const handleLogin = (userData) => {
    setIsLoggedIn(true);
    setUser(userData);
    localStorage.setItem('user', JSON.stringify(userData));
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setUser('');
    localStorage.removeItem('user');
  };
  
  return (
    <Router>
      {!user ? (
        <LoginPage onLogin={handleLogin} />
      ) : (
        <div>
          <Navbar username={user.username} userImage={user.userImage} position={user.position} email={user.email} userRoles={user?.userRoles || []} onLogout={handleLogout} />
          <Sidebar userRoles={user?.userRoles || []} />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/expense-entry/*" element={<Heading username={user.username} userRoles={user?.userRoles || []}/>} />
            <Route path="/designtool/*" element={<DHeading userRoles={user?.userRoles || []}/>} />
            <Route path="/invoice-bill/*" element={<InHeading userRoles={user?.userRoles || []}/>} />
            <Route path="/paints/*" element={<PHeading userRoles={user?.userRoles || []}/>} />
            <Route path="/rccal/*" element={<RcHeading userRoles={user?.userRoles || []}/>} />
            <Route path="/bath/*" element={<BHeading userRoles={user?.userRoles || []}/>} />
            <Route path="/switch/*" element={<SHeading userRoles={user?.userRoles || []}/>} />
            <Route path="/weekly-payment/*" element={<WeeklyPaymentHeading userRoles={user?.userRoles || []}/>} />
            <Route path="/rent/*" element={<RHeading userRoles={user?.userRoles || []}/>} />
            <Route path="/masonary/*" element={<MHeading userRoles={user?.userRoles || []}/>} />
            <Route path="/carpentry/*" element={<CHeading />} userRoles={user?.userRoles || []}/>
            <Route path="/entrychecklist/*" element={<BillHeading userRoles={user?.userRoles || []}/>} />
            <Route path='/purchaseorder/*' element={<PurchaseHeading userRoles={user?.userRoles || []}/>} />
            <Route path='/user_manage/*' element={<ManageHeading userRoles={user?.userRoles || []}/>} />
            <Route path='/attendance' element={<Attendancelog />}/>
            <Route path="*" element={<Navigate to="/" />} />
          </Routes>
        </div>
      )}
    </Router>
  );
}

export default App;
