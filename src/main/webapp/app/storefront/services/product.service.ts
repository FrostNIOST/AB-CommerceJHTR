import axios from 'axios';

export interface IProduct {
  id: string;
  productName: string;
  description?: string;
  purchasePrice?: number;
  sellingPrice: number;
  imgProduct?: string;
  imgProductContentType?: string;
  slug: string;
  stock: number;
  state: string;
  subCategory?: {
    id: string;
    name: string;
  };
}

export const getProductImageUrl = (product: IProduct): string => {
  if (product.imgProduct && product.imgProductContentType) {
    return `data:${product.imgProductContentType};base64,${product.imgProduct}`;
  }
  return 'https://via.placeholder.com/300x300?text=Sin+Imagen';
};

const API_URL = '/api/products';

export const getProducts = async (): Promise<IProduct[]> => {
  try {
    const response = await axios.get<IProduct[]>(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error al cargar productos:', error);
    return [];
  }
};

export const getBestSellers = async (limit: number = 8): Promise<IProduct[]> => {
  const products = await getProducts();
  return products.slice(0, limit);
};
