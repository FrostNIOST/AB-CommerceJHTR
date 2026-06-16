import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { byteSize, openFile } from 'react-jhipster';
import { Link, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './product.reducer';

export const ProductDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const productEntity = useAppSelector(state => state.product.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productDetailsHeading">Product</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{productEntity.id}</dd>
          <dt>
            <span id="productName">Product Name</span>
          </dt>
          <dd>{productEntity.productName}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{productEntity.description}</dd>
          <dt>
            <span id="purchasePrice">Purchase Price</span>
          </dt>
          <dd>{productEntity.purchasePrice}</dd>
          <dt>
            <span id="sellingPrice">Selling Price</span>
          </dt>
          <dd>{productEntity.sellingPrice}</dd>
          <dt>
            <span id="imgProduct">Img Product</span>
          </dt>
          <dd>
            {productEntity.imgProduct ? (
              <div>
                {productEntity.imgProductContentType ? (
                  <a onClick={openFile(productEntity.imgProductContentType, productEntity.imgProduct)}>
                    <img
                      src={`data:${productEntity.imgProductContentType};base64,${productEntity.imgProduct}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {productEntity.imgProductContentType}, {byteSize(productEntity.imgProduct)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="slug">Slug</span>
          </dt>
          <dd>{productEntity.slug}</dd>
          <dt>
            <span id="stock">Stock</span>
          </dt>
          <dd>{productEntity.stock}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{productEntity.state}</dd>
          <dt>Sub Category</dt>
          <dd>{productEntity.subCategory ? productEntity.subCategory.subCategoryName : ''}</dd>
        </dl>
        <Button as={Link as any} to="/product" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/product/${productEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductDetail;
