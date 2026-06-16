import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { Link, useParams } from 'react-router';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './address.reducer';

export const AddressDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const addressEntity = useAppSelector(state => state.address.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="addressDetailsHeading">Address</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{addressEntity.id}</dd>
          <dt>
            <span id="adress">Adress</span>
          </dt>
          <dd>{addressEntity.adress}</dd>
          <dt>
            <span id="city">City</span>
          </dt>
          <dd>{addressEntity.city}</dd>
          <dt>
            <span id="department">Department</span>
          </dt>
          <dd>{addressEntity.department}</dd>
          <dt>
            <span id="postalCode">Postal Code</span>
          </dt>
          <dd>{addressEntity.postalCode}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{addressEntity.state}</dd>
          <dt>Account</dt>
          <dd>{addressEntity.account ? addressEntity.account.id : ''}</dd>
        </dl>
        <Button as={Link as any} to="/address" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/address/${addressEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AddressDetail;
