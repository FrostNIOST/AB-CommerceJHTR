import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { Link, useParams } from 'react-router';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './payment-method.reducer';

export const PaymentMethodDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const paymentMethodEntity = useAppSelector(state => state.paymentMethod.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paymentMethodDetailsHeading">Payment Method</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{paymentMethodEntity.id}</dd>
          <dt>
            <span id="namePaymentMethod">Name Payment Method</span>
          </dt>
          <dd>{paymentMethodEntity.namePaymentMethod}</dd>
          <dt>
            <span id="cod">Cod</span>
          </dt>
          <dd>{paymentMethodEntity.cod}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{paymentMethodEntity.description}</dd>
          <dt>
            <span id="type">Type</span>
          </dt>
          <dd>{paymentMethodEntity.type}</dd>
          <dt>
            <span id="apiKey">Api Key</span>
          </dt>
          <dd>{paymentMethodEntity.apiKey}</dd>
          <dt>
            <span id="endpoint">Endpoint</span>
          </dt>
          <dd>{paymentMethodEntity.endpoint}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{paymentMethodEntity.state}</dd>
        </dl>
        <Button as={Link as any} to="/payment-method" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/payment-method/${paymentMethodEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default PaymentMethodDetail;
