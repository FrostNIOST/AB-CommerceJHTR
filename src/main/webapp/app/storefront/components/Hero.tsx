import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Autoplay } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';

const Hero: React.FC = () => {
  // ✅ Rutas absolutas desde la raíz del webapp
  const categories = [
    { name: 'Celulares', icon: '/Image/icon-phone.png' },
    { name: 'Laptops', icon: '/Image/icon-laptops.png' },
    { name: 'Videojuegos', icon: '/Image/icon-games.png' },
    { name: 'Televisiones', icon: '/Image/icon-television.png' },
    { name: 'Parlantes', icon: '/Image/icon-speaker.png' },
    { name: 'Audífonos', icon: '/Image/icon-headphone.png' },
  ];

  const slides = [
    {
      id: 1,
      className: 'hero-item1',
      tag: 'Oferta del día',
      title: 'Descuentos en Ipads',
      subtitle: 'mira las ofertas',
      description: 'Envíos gratis',
      price: '$499.99',
      image: '/Image/slide1-ipad.png',
    },
    {
      id: 2,
      className: 'hero-item2',
      tag: 'Oferta fin de semana',
      title: 'Lo último Lo mejor',
      subtitle: 'Increíbles descuentos',
      description: 'Por',
      price: '$399.99',
      image: '/Image/slide1-watches.png',
    },
    {
      id: 3,
      className: 'hero-item3',
      tag: 'Audífonos exclusivos',
      title: 'Descuento 50% en audífonos',
      subtitle: '',
      description: '',
      price: '',
      image: '/Image/slide1-iphone.png',
    },
  ];

  return (
    <section className="hero">
      {/* Carrusel de Categorías */}
      <Swiper
        modules={[Navigation, Pagination]}
        spaceBetween={20}
        slidesPerView={6}
        breakpoints={{
          0: { slidesPerView: 2 },
          500: { slidesPerView: 3 },
          768: { slidesPerView: 4 },
          1000: { slidesPerView: 5 },
          1200: { slidesPerView: 6 },
        }}
        className="hero-ctg-swiper"
      >
        {categories.map((category, index) => (
          <SwiperSlide key={index}>
            <div className="product-ctg-item">
              <img src={category.icon} alt={category.name} />
              <span>{category.name}</span>
            </div>
          </SwiperSlide>
        ))}
      </Swiper>

      {/* Carrusel Principal */}
      <Swiper
        modules={[Navigation, Pagination, Autoplay]}
        spaceBetween={30}
        slidesPerView={1}
        navigation
        pagination={{ clickable: true }}
        autoplay={{ delay: 5000, disableOnInteraction: false }}
        className="hero-swiper"
      >
        {slides.map(slide => (
          <SwiperSlide key={slide.id}>
            <div className={`hero-item ${slide.className}`}>
              <div className="hero-content">
                <span className="hero-text">{slide.tag}</span>
                <h2>
                  {slide.title}
                  {slide.subtitle && <span>{slide.subtitle}</span>}
                </h2>
                {slide.description && <p>{slide.description}</p>}
                {slide.price && (
                  <h6>
                    {slide.description} <span className="price">{slide.price}</span>
                  </h6>
                )}
                <button className="btn">
                  <a href="#">Comprar ahora</a>
                </button>
              </div>
              <div className="hero-image">
                <img src={slide.image} alt={slide.title} />
              </div>
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </section>
  );
};

export default Hero;
