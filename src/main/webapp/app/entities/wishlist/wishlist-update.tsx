import React, { useEffect } from 'react';
import { Button, Col, FormText, Row } from 'react-bootstrap';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { Link, useNavigate, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntities as getAccountUS } from 'app/entities/account-u/account-u.reducer';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { State } from 'app/shared/model/enumerations/state.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

import { createEntity, getEntity, reset, updateEntity } from './wishlist.reducer';

export const WishlistUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const accountUS = useAppSelector(state => state.accountU.entities);
  const products = useAppSelector(state => state.product.entities);
  const wishlistEntity = useAppSelector(state => state.wishlist.entity);
  const loading = useAppSelector(state => state.wishlist.loading);
  const updating = useAppSelector(state => state.wishlist.updating);
  const updateSuccess = useAppSelector(state => state.wishlist.updateSuccess);
  const stateValues = Object.keys(State);

  const handleClose = () => {
    navigate(`/wishlist${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAccountUS({}));
    dispatch(getProducts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.updateAt = convertDateTimeToServer(values.updateAt);

    const entity = {
      ...wishlistEntity,
      ...values,
      account: accountUS.find(it => it.id.toString() === values.account?.toString()),
      productses: mapIdList(values.productses),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          updateAt: displayDefaultDateTime(),
        }
      : {
          state: 'ACTIVE',
          ...wishlistEntity,
          updateAt: convertDateTimeFromServer(wishlistEntity.updateAt),
          account: wishlistEntity?.account?.id,
          productses: wishlistEntity?.productses?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="abcommerceApp.wishlist.home.createOrEditLabel" data-cy="WishlistCreateUpdateHeading">
            Crear o editar Wishlist
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew && <ValidatedField name="id" required readOnly id="wishlist-id" label="ID" validate={{ required: true }} />}
              <ValidatedField label="State" id="wishlist-state" name="state" data-cy="state" type="select">
                {stateValues.map(state => (
                  <option value={state} key={state}>
                    {state}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Update At"
                id="wishlist-updateAt"
                name="updateAt"
                data-cy="updateAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                }}
              />
              <ValidatedField id="wishlist-account" name="account" data-cy="account" label="Account" type="select" required>
                <option value="" key="0" />
                {accountUS
                  ? accountUS.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>Este campo es obligatorio.</FormText>
              <ValidatedField label="Products" id="wishlist-products" data-cy="products" type="select" multiple name="productses">
                <option value="" key="0" />
                {products
                  ? products.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.productName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button as={Link as any} id="cancel-save" data-cy="entityCreateCancelButton" to="/wishlist" replace variant="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Volver</span>
              </Button>
              &nbsp;
              <Button variant="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Guardar
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default WishlistUpdate;
