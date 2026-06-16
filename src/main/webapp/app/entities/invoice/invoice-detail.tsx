import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { Link, useParams } from 'react-router';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './invoice.reducer';

export const InvoiceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const invoiceEntity = useAppSelector(state => state.invoice.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="invoiceDetailsHeading">Invoice</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{invoiceEntity.id}</dd>
          <dt>
            <span id="pref">Pref</span>
          </dt>
          <dd>{invoiceEntity.pref}</dd>
          <dt>
            <span id="invoiceNumber">Invoice Number</span>
          </dt>
          <dd>{invoiceEntity.invoiceNumber}</dd>
          <dt>
            <span id="paymentStatus">Payment Status</span>
          </dt>
          <dd>{invoiceEntity.paymentStatus}</dd>
          <dt>
            <span id="refTransaction">Ref Transaction</span>
          </dt>
          <dd>{invoiceEntity.refTransaction}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{invoiceEntity.state}</dd>
          <dt>Order</dt>
          <dd>{invoiceEntity.order ? invoiceEntity.order.orderNumber : ''}</dd>
        </dl>
        <Button as={Link as any} to="/invoice" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/invoice/${invoiceEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default InvoiceDetail;
