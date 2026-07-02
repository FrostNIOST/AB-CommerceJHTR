import React from 'react';
import Hero from '../components/Hero';
import Banner from '../components/Banner';
import BestSeller from '../components/BestSeller';

const HomePage: React.FC = () => {
  return (
    <>
      <Hero />
      <Banner />
      <BestSeller />
      <main style={{ padding: '20px' }}>
        <h1>Bienvenido a AB-Commerce</h1>
        <p>Aquí irá el resto del contenido de la página de inicio.</p>
      </main>
    </>
  );
};

export default HomePage;
