/*
 * Copyright 2018 Mathieu Cartoixa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mcartoixa.ant.sfdx.force.org;

import com.mcartoixa.ant.sfdx.SfdxTask;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Commandline;

/**
 *
 * @author Mathieu Cartoixa
 */
public class DeleteTask extends SfdxTask {

    /* default */ class JsonParser extends SfdxTask.JsonParser {

        /* default */ JsonParser() {
            super();
        }

        @Override
        protected void handleValue(final String property, final String key, final String value) {
            super.handleValue(property, key, value);

            if (!DeleteTask.this.getQuiet()) {
                switch (key) {
                    case "orgid":
                        this.log("Org " + value + "deleted", Project.MSG_INFO);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public DeleteTask() {
        super();
    }

    @Override
    protected String getCommand() {
        return "force:org:delete";
    }

    public void setTargetUserName(final String userName) {
        if (userName != null && !userName.isEmpty()) {
            final Commandline.Argument arg = getCommandline().createArgument();
            arg.setPrefix("-u");
            arg.setValue(userName);
        }
    }

    public void setTargetDevHubUserName(final String devHubUserName) {
        if (devHubUserName != null && !devHubUserName.isEmpty()) {
            final Commandline.Argument arg = getCommandline().createArgument();
            arg.setPrefix("-v");
            arg.setValue(devHubUserName);
        }
    }

    @Override
    protected void createArguments() {
        this.getCommandline().createArgument()
                .setValue("-p"); // no prompt
    }
}
