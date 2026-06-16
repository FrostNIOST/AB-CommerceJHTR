import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { TextFormat } from 'react-jhipster';
import { Link, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './wishlist.reducer';

export const WishlistDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const wishlistEntity = useAppSelector(state => state.wishlist.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="wishlistDetailsHeading">Wishlist</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{wishlistEntity.id}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{wishlistEntity.state}</dd>
          <dt>
            <span id="updateAt">Update At</span>
          </dt>
          <dd>{wishlistEntity.updateAt ? <TextFormat value={wishlistEntity.updateAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>Account</dt>
          <dd>{wishlistEntity.account ? wishlistEntity.account.id : ''}</dd>
          <dt>Products</dt>
          <dd>
            {wishlistEntity.productses
              ? wishlistEntity.productses.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.productName}</a>
                    {wishlistEntity.productses && i === wishlistEntity.productses.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button as={Link as any} to="/wishlist" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/wishlist/${wishlistEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default WishlistDetail;
