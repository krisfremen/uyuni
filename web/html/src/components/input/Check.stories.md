```jsx
import { SubmitButton } from "components/buttons";

import { Check } from "./Check";
import { Form } from "./Form";

const model = {
  force: false,
};

<Form
  model={model}
  onSubmit={() => alert(`May the force${model["force"] ? "" : " NOT"} be with you`)}
  divClass="col-md-12"
  formDirection="form-horizontal"
  onChange={(newModel) => {
    model["force"] = newModel["force"];
  }}
>
  <Check name="force" label="Force action" divClass="col-md-6 col-md-offset-3" />
  <SubmitButton id="submit-btn" className="btn-success" text={t("Submit")} />
</Form>
```
