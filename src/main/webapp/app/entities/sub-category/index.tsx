import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import SubCategory from './sub-category';
import SubCategoryDeleteDialog from './sub-category-delete-dialog';
import SubCategoryDetail from './sub-category-detail';
import SubCategoryUpdate from './sub-category-update';

const SubCategoryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<SubCategory />} />
    <Route path="new" element={<SubCategoryUpdate />} />
    <Route path=":id">
      <Route index element={<SubCategoryDetail />} />
      <Route path="edit" element={<SubCategoryUpdate />} />
      <Route path="delete" element={<SubCategoryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SubCategoryRoutes;
