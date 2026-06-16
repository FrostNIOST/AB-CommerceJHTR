import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { Link, useParams } from 'react-router';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './document-type.reducer';

export const DocumentTypeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const documentTypeEntity = useAppSelector(state => state.documentType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="documentTypeDetailsHeading">Document Type</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{documentTypeEntity.id}</dd>
          <dt>
            <span id="cod">Cod</span>
          </dt>
          <dd>{documentTypeEntity.cod}</dd>
          <dt>
            <span id="documentType">Document Type</span>
          </dt>
          <dd>{documentTypeEntity.documentType}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{documentTypeEntity.state}</dd>
        </dl>
        <Button as={Link as any} to="/document-type" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/document-type/${documentTypeEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default DocumentTypeDetail;
