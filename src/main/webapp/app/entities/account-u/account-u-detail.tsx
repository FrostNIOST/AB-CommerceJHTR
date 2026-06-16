import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { Link, useParams } from 'react-router';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './account-u.reducer';

export const AccountUDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const accountUEntity = useAppSelector(state => state.accountU.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="accountUDetailsHeading">Account U</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{accountUEntity.id}</dd>
          <dt>
            <span id="documentNumber">Document Number</span>
          </dt>
          <dd>{accountUEntity.documentNumber}</dd>
          <dt>
            <span id="firstName">First Name</span>
          </dt>
          <dd>{accountUEntity.firstName}</dd>
          <dt>
            <span id="secondName">Second Name</span>
          </dt>
          <dd>{accountUEntity.secondName}</dd>
          <dt>
            <span id="firstLastName">First Last Name</span>
          </dt>
          <dd>{accountUEntity.firstLastName}</dd>
          <dt>
            <span id="secondLastName">Second Last Name</span>
          </dt>
          <dd>{accountUEntity.secondLastName}</dd>
          <dt>User</dt>
          <dd>{accountUEntity.user ? accountUEntity.user.login : ''}</dd>
          <dt>Document Type</dt>
          <dd>{accountUEntity.documentType ? accountUEntity.documentType.documentType : ''}</dd>
        </dl>
        <Button as={Link as any} to="/account-u" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/account-u/${accountUEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AccountUDetail;
