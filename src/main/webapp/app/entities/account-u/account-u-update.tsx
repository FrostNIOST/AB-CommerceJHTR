import React, { useEffect } from 'react';
import { Button, Col, FormText, Row } from 'react-bootstrap';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { Link, useNavigate, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntities as getDocumentTypes } from 'app/entities/document-type/document-type.reducer';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';

import { createEntity, getEntity, reset, updateEntity } from './account-u.reducer';

export const AccountUUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const documentTypes = useAppSelector(state => state.documentType.entities);
  const accountUEntity = useAppSelector(state => state.accountU.entity);
  const loading = useAppSelector(state => state.accountU.loading);
  const updating = useAppSelector(state => state.accountU.updating);
  const updateSuccess = useAppSelector(state => state.accountU.updateSuccess);

  const handleClose = () => {
    navigate(`/account-u${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getUsers({}));
    dispatch(getDocumentTypes({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...accountUEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user?.toString()),
      documentType: documentTypes.find(it => it.id.toString() === values.documentType?.toString()),
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
          ...accountUEntity,
          user: accountUEntity?.user?.id,
          documentType: accountUEntity?.documentType?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="abcommerceApp.accountU.home.createOrEditLabel" data-cy="AccountUCreateUpdateHeading">
            Crear o editar Account U
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew && <ValidatedField name="id" required readOnly id="account-u-id" label="ID" validate={{ required: true }} />}
              <ValidatedField
                label="Document Number"
                id="account-u-documentNumber"
                name="documentNumber"
                data-cy="documentNumber"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  maxLength: { value: 20, message: 'Este campo no puede superar más de 20 caracteres.' },
                }}
              />
              <ValidatedField
                label="First Name"
                id="account-u-firstName"
                name="firstName"
                data-cy="firstName"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  maxLength: { value: 50, message: 'Este campo no puede superar más de 50 caracteres.' },
                }}
              />
              <ValidatedField
                label="Second Name"
                id="account-u-secondName"
                name="secondName"
                data-cy="secondName"
                type="text"
                validate={{
                  maxLength: { value: 50, message: 'Este campo no puede superar más de 50 caracteres.' },
                }}
              />
              <ValidatedField
                label="First Last Name"
                id="account-u-firstLastName"
                name="firstLastName"
                data-cy="firstLastName"
                type="text"
                validate={{
                  required: { value: true, message: 'Este campo es obligatorio.' },
                  maxLength: { value: 50, message: 'Este campo no puede superar más de 50 caracteres.' },
                }}
              />
              <ValidatedField
                label="Second Last Name"
                id="account-u-secondLastName"
                name="secondLastName"
                data-cy="secondLastName"
                type="text"
                validate={{
                  maxLength: { value: 50, message: 'Este campo no puede superar más de 50 caracteres.' },
                }}
              />
              <ValidatedField id="account-u-user" name="user" data-cy="user" label="User" type="select" required>
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>Este campo es obligatorio.</FormText>
              <ValidatedField
                id="account-u-documentType"
                name="documentType"
                data-cy="documentType"
                label="Document Type"
                type="select"
                required
              >
                <option value="" key="0" />
                {documentTypes
                  ? documentTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.documentType}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>Este campo es obligatorio.</FormText>
              <Button as={Link as any} id="cancel-save" data-cy="entityCreateCancelButton" to="/account-u" replace variant="info">
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

export default AccountUUpdate;
