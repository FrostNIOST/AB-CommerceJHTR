import React, { useEffect } from 'react';
import { Button, Col, FormText, Row } from 'react-bootstrap';
import { ValidatedField, ValidatedForm, isNumber } from 'react-jhipster';
import { Link, useNavigate, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntities as getAccountUS } from 'app/entities/account-u/account-u.reducer';
import { getEntities as getAddresses } from 'app/entities/address/address.reducer';
import { getEntities as getPaymentMethods } from 'app/entities/payment-method/payment-method.reducer';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { State } from 'app/shared/model/enumerations/state.model';

import { createEntity, getEntity, reset, updateEntity } from './order.reducer';

export const OrderUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const paymentMethods = useAppSelector(state => state.paymentMethod.entities);
  const addresses = useAppSelector(state => state.address.entities);
  const accountUS = useAppSelector(state => state.accountU.entities);
  const orderEntity = useAppSelector(state => state.order.entity);
  const loading = useAppSelector(state => state.order.loading);
  const updating = useAppSelector(state => state.order.updating);
  const updateSuccess = useAppSelector(state => state.order.updateSuccess);
  const orderStatusValues = Object.keys(OrderStatus);
  const stateValues = Object.keys(State);

  const handleClose = () => {
    navigate(`/order${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getPaymentMethods({}));
    dispatch(getAddresses({}));
    dispatch(getAccountUS({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.subtotal !== undefined && typeof values.subtotal !== 'number') {
      values.subtotal = Number(values.subtotal);
    }
    if (values.subtotalIva !== undefined && typeof values.subtotalIva !== 'number') {
      values.subtotalIva = Number(values.subtotalIva);
    }
    if (values.shippingCost !== undefined && typeof values.shippingCost !== 'number') {
      values.shippingCost = Number(values.shippingCost);
    }
    if (values.total !== undefined && typeof values.total !== 'number') {
      values.total = Number(values.total);
    }

    const entity = {
      ...orderEntity,
      ...values,
      paymentMethod: paymentMethods.find(it => it.id.toString() === values.paymentMethod?.toString()),
      shippingAddress: addresses.find(it => it.id.toString() === values.shippingAddress?.toString()),
      account: accountUS.find(it => it.id.toString() === values.account?.toString()),
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
          status: 'PENDING',
          state: 'ACTIVE',
          ...orderEntity,
          paymentMethod: orderEntity?.paymentMethod?.id,
          shippingAddress: orderEntity?.shippingAddress?.id,
          account: orderEntity?.account?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="abcommerceApp.order.home.createOrEditLabel" data-cy="OrderCreateUpdateHeading">
            Crear o editar Order
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew && <ValidatedField name="id" required readOnly id="order-id" label="ID" validate={{ required: true }} />}
              <ValidatedField
                label="Order Number"
                id="order-orderNumber"
                name="orderNumber"
                data-cy="orderNumber"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                }}
              />
              <ValidatedField
                label="Subtotal"
                id="order-subtotal"
                name="subtotal"
                data-cy="subtotal"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  min: { value: 0, message: 'Este campo debe ser mayor que 0.' },
                  validate: v => isNumber(v) || 'Este campo debe ser un número.',
                }}
              />
              <ValidatedField
                label="Subtotal Iva"
                id="order-subtotalIva"
                name="subtotalIva"
                data-cy="subtotalIva"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  min: { value: 0, message: 'Este campo debe ser mayor que 0.' },
                  validate: v => isNumber(v) || 'Este campo debe ser un número.',
                }}
              />
              <ValidatedField
                label="Shipping Cost"
                id="order-shippingCost"
                name="shippingCost"
                data-cy="shippingCost"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  min: { value: 0, message: 'Este campo debe ser mayor que 0.' },
                  validate: v => isNumber(v) || 'Este campo debe ser un número.',
                }}
              />
              <ValidatedField
                label="Total"
                id="order-total"
                name="total"
                data-cy="total"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  min: { value: 1, message: 'Este campo debe ser mayor que 1.' },
                  validate: v => isNumber(v) || 'Este campo debe ser un número.',
                }}
              />
              <ValidatedField label="Status" id="order-status" name="status" data-cy="status" type="select">
                {orderStatusValues.map(orderStatus => (
                  <option value={orderStatus} key={orderStatus}>
                    {orderStatus}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="State" id="order-state" name="state" data-cy="state" type="select">
                {stateValues.map(state => (
                  <option value={state} key={state}>
                    {state}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                id="order-paymentMethod"
                name="paymentMethod"
                data-cy="paymentMethod"
                label="Payment Method"
                type="select"
                required
              >
                <option value="" key="0" />
                {paymentMethods
                  ? paymentMethods.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.namePaymentMethod}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>Este campo es obligatorio.</FormText>
              <ValidatedField
                id="order-shippingAddress"
                name="shippingAddress"
                data-cy="shippingAddress"
                label="Shipping Address"
                type="select"
                required
              >
                <option value="" key="0" />
                {addresses
                  ? addresses.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.adress}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>Este campo es obligatorio.</FormText>
              <ValidatedField id="order-account" name="account" data-cy="account" label="Account" type="select" required>
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
              <Button as={Link as any} id="cancel-save" data-cy="entityCreateCancelButton" to="/order" replace variant="info">
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

export default OrderUpdate;
