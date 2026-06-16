import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { Link, useParams } from 'react-router';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './sub-category.reducer';

export const SubCategoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const subCategoryEntity = useAppSelector(state => state.subCategory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="subCategoryDetailsHeading">Sub Category</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{subCategoryEntity.id}</dd>
          <dt>
            <span id="subCategoryName">Sub Category Name</span>
          </dt>
          <dd>{subCategoryEntity.subCategoryName}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{subCategoryEntity.description}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{subCategoryEntity.state}</dd>
          <dt>Category</dt>
          <dd>{subCategoryEntity.category ? subCategoryEntity.category.categoryName : ''}</dd>
        </dl>
        <Button as={Link as any} to="/sub-category" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/sub-category/${subCategoryEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default SubCategoryDetail;
