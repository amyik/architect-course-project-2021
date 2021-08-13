package com.sds.common.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateProjectDto {

    private CreateProjectDto.Project project;

    private CreateProjectDto.ToolInfo codeRepoTool;
    private CreateProjectDto.ToolInfo imageRepoTool;
    private CreateProjectDto.ToolInfo buildTool;
    private CreateProjectDto.ToolInfo otherTool;

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    @ToString
    public static class Project {
        private String name;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    @ToString
    public static class ToolInfo {
        private boolean use;
        private String type;
        private String name;
        private String url;
    }

    public static CreateProjectDto makeTestSample() {

        return CreateProjectDto.builder()
                .project(Project.builder().name("myProject").build())
                .codeRepoTool(ToolInfo.builder().use(true).type("GITHUB").name("myGITHUB").url("http://github.com/myGITHUB").build())
                .imageRepoTool(ToolInfo.builder().use(true).type("REDII").name("myREDII").url("http://redii.net/myREDII").build())
                .buildTool(ToolInfo.builder().use(true).type("JENKINS").name("myJENKINS").url("http://jenkins.com/myJENKINS").build())
                .otherTool(ToolInfo.builder().use(false).build())
                .build();
    }
}
