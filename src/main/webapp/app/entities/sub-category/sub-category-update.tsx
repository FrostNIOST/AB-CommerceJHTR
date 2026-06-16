import React, { useEffect } from 'react';
import { Button, Col, FormText, Row } from 'react-bootstrap';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { Link, useNavigate, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntities as getCategories } from 'app/entities/category/category.reducer';
import { State } from 'app/shared/model/enumerations/state.model';

import { createEntity, getEntity, reset, updateEntity } from './sub-category.reducer';

export const SubCategoryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categories = useAppSelector(state => state.category.entities);
  const subCategoryEntity = useAppSelector(state => state.subCategory.entity);
  const loading = useAppSelector(state => state.subCategory.loading);
  const updating = useAppSelector(state => state.subCategory.updating);
  const updateSuccess = useAppSelector(state => state.subCategory.updateSuccess);
  const stateValues = Object.keys(State);

  const handleClose = () => {
    navigate(`/sub-category${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCategories({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...subCategoryEntity,
      ...values,
      category: categories.find(it => it.id.toString() === values.category?.toString()),
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
          ...subCategoryEntity,
          category: subCategoryEntity?.category?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="abcommerceApp.subCategory.home.createOrEditLabel" data-cy="SubCategoryCreateUpdateHeading">
            Crear o editar Sub Category
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew && <ValidatedField name="id" required readOnly id="sub-category-id" label="ID" validate={{ required: true }} />}
              <ValidatedField
                label="Sub Category Name"
                id="sub-category-subCategoryName"
                name="subCategoryName"
                data-cy="subCategoryName"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  minLength: { value: 5, message: 'Este campo requiere al menos 5 caracteres.' },
                  maxLength: { value: 70, message: 'Este campo no puede superar más de 70 caracteres.' },
                }}
              />
              <ValidatedField
                label="Description"
                id="sub-category-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  minLength: { value: 10, message: 'Este campo requiere al menos 10 caracteres.' },
                  maxLength: { value: 200, message: 'Este campo no puede superar más de 200 caracteres.' },
                }}
              />
              <ValidatedField label="State" id="sub-category-state" name="state" data-cy="state" type="select">
                {stateValues.map(state => (
                  <option value={state} key={state}>
                    {state}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField id="sub-category-category" name="category" data-cy="category" label="Category" type="select" required>
                <option value="" key="0" />
                {categories
                  ? categories.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.categoryName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>Este campo es obligatorio.</FormText>
              <Button as={Link as any} id="cancel-save" data-cy="entityCreateCancelButton" to="/sub-category" replace variant="info">
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

export default SubCategoryUpdate;
