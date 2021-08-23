/*
 * Copyright 2021 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.auditui.screen.snapshot;

import io.jmix.audit.snapshot.model.EntityClassPropertyDifferenceModel;
import io.jmix.audit.snapshot.model.EntityCollectionPropertyDifferenceModel;
import io.jmix.audit.snapshot.model.EntityPropertyDifferenceModel;
import io.jmix.ui.component.Table;

public class DiffStyleProvider implements Table.StyleProvider<EntityPropertyDifferenceModel> {

    @Override
    public String getStyleName(EntityPropertyDifferenceModel entity, String property) {
        if (property != null) {
            if ("name".equals(property)) {
                if (entity instanceof EntityClassPropertyDifferenceModel) {
                    switch (entity.getItemState()) {
                        case Added:
                            return "addedItem";

                        case Modified:
                            return "modifiedItem";

                        case Normal:
                            if (((EntityClassPropertyDifferenceModel) entity).isLinkChange())
                                return "chainItem";
                            else
                                return "modifiedItem";

                        case Removed:
                            return "removedItem";
                    }
                } else if (entity instanceof EntityCollectionPropertyDifferenceModel) {
                    return "categoryItem";
                }

            }
        }
        return null;
    }
}