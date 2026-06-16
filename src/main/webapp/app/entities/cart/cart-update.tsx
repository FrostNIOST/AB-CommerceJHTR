import React, { useEffect } from 'react';
import { Button, Col, FormText, Row } from 'react-bootstrap';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { Link, useNavigate, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntities as getAccountUS } from 'app/entities/account-u/account-u.reducer';
import { getEntities as getCartItems } from 'app/entities/cart-item/cart-item.reducer';
import { State } from 'app/shared/model/enumerations/state.model';
import { mapIdList } from 'app/shared/util/entity-utils';

import { createEntity, getEntity, reset, updateEntity } from './cart.reducer';

export const CartUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const accountUS = useAppSelector(state => state.accountU.entities);
  const cartItems = useAppSelector(state => state.cartItem.entities);
  const cartEntity = useAppSelector(state => state.cart.entity);
  const loading = useAppSelector(state => state.cart.loading);
  const updating = useAppSelector(state => state.cart.updating);
  const updateSuccess = useAppSelector(state => state.cart.updateSuccess);
  const stateValues = Object.keys(State);

  const handleClose = () => {
    navigate(`/cart${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAccountUS({}));
    dispatch(getCartItems({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...cartEntity,
      ...values,
      account: accountUS.find(it => it.id.toString() === values.account?.toString()),
      itemses: mapIdList(values.itemses),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          state: 'ACTIVE',
          ...cartEntity,
          account: cartEntity?.account?.id,
          itemses: cartEntity?.itemses?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="abcommerceApp.cart.home.createOrEditLabel" data-cy="CartCreateUpdateHeading">
            Crear o editar Cart
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew && <ValidatedField name="id" required readOnly id="cart-id" label="ID" validate={{ required: true }} />}
              <ValidatedField label="State" id="cart-state" name="state" data-cy="state" type="select">
                {stateValues.map(state => (
                  <option value={state} key={state}>
                    {state}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField id="cart-account" name="account" data-cy="account" label="Account" type="select" required>
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
              <ValidatedField label="Items" id="cart-items" data-cy="items" type="select" multiple name="itemses">
                <option value="" key="0" />
                {cartItems
                  ? cartItems.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button as={Link as any} id="cancel-save" data-cy="entityCreateCancelButton" to="/cart" replace variant="info">
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

export default CartUpdate;
