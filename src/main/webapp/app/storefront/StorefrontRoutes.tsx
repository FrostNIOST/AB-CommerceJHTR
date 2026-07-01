import React from 'react';
import { Routes, Route } from 'react-router-dom';
import StorefrontHeader from './components/StorefrontHeader';
import HomePage from './pages/HomePage';
import './styles/global.css'; // Importar estilos globales

const StorefrontRoutes = () => {
  return (
    <div className="storefront-app">
      <StorefrontHeader />
      <Routes>
        <Route path="/" element={<HomePage />} />
      </Routes>
    </div>
  );
};

export default StorefrontRoutes;
