import React, { useEffect } from 'react';
import { Button, Col, FormText, Row } from 'react-bootstrap';
import { ValidatedBlobField, ValidatedField, ValidatedForm, isNumber } from 'react-jhipster';
import { Link, useNavigate, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntities as getSubCategories } from 'app/entities/sub-category/sub-category.reducer';
import { State } from 'app/shared/model/enumerations/state.model';

import { createEntity, getEntity, reset, updateEntity } from './product.reducer';

export const ProductUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const subCategories = useAppSelector(state => state.subCategory.entities);
  const productEntity = useAppSelector(state => state.product.entity);
  const loading = useAppSelector(state => state.product.loading);
  const updating = useAppSelector(state => state.product.updating);
  const updateSuccess = useAppSelector(state => state.product.updateSuccess);
  const stateValues = Object.keys(State);

  const handleClose = () => {
    navigate(`/product${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getSubCategories({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.purchasePrice !== undefined && typeof values.purchasePrice !== 'number') {
      values.purchasePrice = Number(values.purchasePrice);
    }
    if (values.sellingPrice !== undefined && typeof values.sellingPrice !== 'number') {
      values.sellingPrice = Number(values.sellingPrice);
    }
    if (values.stock !== undefined && typeof values.stock !== 'number') {
      values.stock = Number(values.stock);
    }

    const entity = {
      ...productEntity,
      ...values,
      subCategory: subCategories.find(it => it.id.toString() === values.subCategory?.toString()),
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
          ...productEntity,
          subCategory: productEntity?.subCategory?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="abcommerceApp.product.home.createOrEditLabel" data-cy="ProductCreateUpdateHeading">
            Crear o editar Product
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew && <ValidatedField name="id" required readOnly id="product-id" label="ID" validate={{ required: true }} />}
              <ValidatedField
                label="Product Name"
                id="product-productName"
                name="productName"
                data-cy="productName"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  minLength: { value: 5, message: 'Este campo requiere al menos 5 caracteres.' },
                  maxLength: { value: 70, message: 'Este campo no puede superar más de 70 caracteres.' },
                }}
              />
              <ValidatedField
                label="Description"
                id="product-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  minLength: { value: 10, message: 'Este campo requiere al menos 10 caracteres.' },
                  maxLength: { value: 200, message: 'Este campo no puede superar más de 200 caracteres.' },
                }}
              />
              <ValidatedField
                label="Purchase Price"
                id="product-purchasePrice"
                name="purchasePrice"
                data-cy="purchasePrice"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  min: { value: 1, message: 'Este campo debe ser mayor que 1.' },
                  validate: v => isNumber(v) || 'Este campo debe ser un número.',
                }}
              />
              <ValidatedField
                label="Selling Price"
                id="product-sellingPrice"
                name="sellingPrice"
                data-cy="sellingPrice"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  min: { value: 1, message: 'Este campo debe ser mayor que 1.' },
                  validate: v => isNumber(v) || 'Este campo debe ser un número.',
                }}
              />
              <ValidatedBlobField
                label="Img Product"
                id="product-imgProduct"
                name="imgProduct"
                data-cy="imgProduct"
                isImage
                accept="image/*"
              />
              <ValidatedField
                label="Slug"
                id="product-slug"
                name="slug"
                data-cy="slug"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  minLength: { value: 3, message: 'Este campo requiere al menos 3 caracteres.' },
                  maxLength: { value: 150, message: 'Este campo no puede superar más de 150 caracteres.' },
                }}
              />
              <ValidatedField
                label="Stock"
                id="product-stock"
                name="stock"
                data-cy="stock"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  min: { value: 0, message: 'Este campo debe ser mayor que 0.' },
                  validate: v => isNumber(v) || 'Este campo debe ser un número.',
                }}
              />
              <ValidatedField label="State" id="product-state" name="state" data-cy="state" type="select">
                {stateValues.map(state => (
                  <option value={state} key={state}>
                    {state}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField id="product-subCategory" name="subCategory" data-cy="subCategory" label="Sub Category" type="select" required>
                <option value="" key="0" />
                {subCategories
                  ? subCategories.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.subCategoryName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>Este campo es obligatorio.</FormText>
              <Button as={Link as any} id="cancel-save" data-cy="entityCreateCancelButton" to="/product" replace variant="info">
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

export default ProductUpdate;
